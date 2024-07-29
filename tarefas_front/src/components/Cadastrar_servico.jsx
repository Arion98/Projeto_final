import { useForm } from "react-hook-form";
import { api } from "../config_axios";
import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import '../css/CadastrarServico.css';

const CadastrarServico = () => {
  const { register, handleSubmit, reset } = useForm();
  const [categorias, setCategorias] = useState([]);
  const [prestadores, setPrestadores] = useState([]);
  const [aviso, setAviso] = useState("");
  const [success, setSuccess] = useState(false); // Estado para controlar a mensagem de sucesso
  const navigate = useNavigate(); // Hook para navegação

  useEffect(() => {
    const fetchCategorias = async () => {
      try {
        const response = await api.get("/categoria");
        setCategorias(response.data || []);
      } catch (error) {
        console.error("Erro ao buscar categorias:", error);
        setCategorias([]);
      }
    };

    const fetchPrestadores = async () => {
      try {
        const response = await api.get("/prestador");
        setPrestadores(response.data || []);
      } catch (error) {
        console.error("Erro ao buscar prestadores:", error);
        setPrestadores([]);
      }
    };

    fetchCategorias();
    fetchPrestadores();
  }, []);

  const salvar = async (campos) => {
    try {
      const response = await api.post('servico', {
        servicoNome: campos.servicoNome,
        servico_preco: parseFloat(campos.servico_preco),
        servico_descricao: campos.servico_descricao,
        servico_informacoesExtras: campos.servico_informacoesExtras,
        categoria: {
          categoria_id: parseInt(campos.categoria_id, 10)
        },
        prestador: {
          prestador_id: parseInt(campos.prestador_id, 10)
        },
        servico_classificacao: campos.servico_classificacao,
      });
      reset();
      setSuccess(true);
      setTimeout(() => {
        navigate("/");
      }, 2000);
      setAviso('Serviço cadastrado com sucesso!');
    } catch (error) {
      console.error('Erro ao cadastrar serviço:', error);
      setAviso('Erro ao cadastrar serviço. Por favor, tente novamente!');
    }
  };

  return (
    <div className="container-fluid bg-dark text-light min-vh-100 d-flex align-items-center">
      <div className="container p-5 bg-light text-dark rounded">
        <h4 className="fst-italic mb-3">Cadastrar Serviço</h4>
        <form onSubmit={handleSubmit(salvar)}>
          <div className="form-group mt-2">
            <label htmlFor="servicoNome">Nome do Serviço:</label>
            <input
              type="text"
              className="form-control"
              id="servicoNome"
              required
              {...register("servicoNome")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="servico_preco">Preço:</label>
            <input
              type="number"
              step="0.01"
              className="form-control"
              id="servico_preco"
              required
              {...register("servico_preco")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="servico_descricao">Descrição:</label>
            <textarea
              className="form-control"
              id="servico_descricao"
              required
              {...register("servico_descricao")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="servico_informacoesExtras">Informações Extras:</label>
            <textarea
              className="form-control"
              id="servico_informacoesExtras"
              {...register("servico_informacoesExtras")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="categoria_id">Categoria:</label>
            <select
              className="form-control"
              id="categoria_id"
              required
              defaultValue=""
              {...register("categoria_id")}
            >
              <option value="" disabled>Selecione uma categoria</option>
              {categorias.map(categoria => (
                <option
                  key={categoria.categoria_id}
                  value={categoria.categoria_id}
                >
                  {categoria.categoria_nome}
                </option>
              ))}
            </select>
          </div>
          <div className="form-group mt-2">
            <label htmlFor="prestador_id">Prestador:</label>
            <select
              className="form-control"
              id="prestador_id"
              required
              defaultValue=""
              {...register("prestador_id")}
            >
              <option value="" disabled>Selecione um prestador</option>
              {prestadores.map(prestador => (
                <option
                  key={prestador.prestador_id}
                  value={prestador.prestador_id}
                >
                  {prestador.prestador_nome}
                </option>
              ))}
            </select>
          </div>
          <div className="form-group mt-2">
            <label htmlFor="servico_classificacao">Classificação:</label>
            <input
              type="text"
              className="form-control"
              id="servico_classificacao"
              {...register("servico_classificacao")}
            />
          </div>
          <div className="d-flex justify-content-between mt-3">
            <input
              type="submit"
              className="btn btn-primary btn-sm"
              value="Enviar"
            />
            <Link to="/" className="btn btn-danger btn-sm ms-3">
              Voltar
            </Link>
          </div>
        </form>
        {aviso && (
          <div
            className={`alert mt-3 ${success ? "alert-success" : "alert-danger"}`}
          >
            {aviso}
          </div>
        )}
      </div>
    </div>
  );
};

export default CadastrarServico;
