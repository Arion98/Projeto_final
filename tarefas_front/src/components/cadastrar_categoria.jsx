import { useForm } from "react-hook-form";
import { api } from "../config_axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import '../css/CadastrarCategoria.css';

const CadastrarCategoria = () => {
  const { register, handleSubmit, reset } = useForm();
  const [aviso, setAviso] = useState("");
  const [success, setSuccess] = useState(false); // Estado para controlar a mensagem de sucesso
  const navigate = useNavigate(); // Hook para navegação

  const salvar = async (campos) => {
    try {
      const response = await api.post('categoria', {
        categoria_nome: campos.categoria_nome,
        categoria_descricao: campos.categoria_descricao,
      });
      reset();
      setSuccess(true);
      setTimeout(() => {
        navigate("/servico");
      }, 2000);
      setAviso('Categoria cadastrada com sucesso!');
    } catch (error) {
      // Tratar erros de requisição
      console.error('Erro ao cadastrar categoria:', error);
      setAviso('Erro ao cadastrar categoria. Por favor, tente novamente!');
    }
  };

  return (
    <div className="container-fluid bg-dark text-light min-vh-100 d-flex align-items-center">
      <div className="container p-5 bg-light text-dark rounded">
        <h4 className="fst-italic mb-3">Cadastrar Categoria</h4>
        <form onSubmit={handleSubmit(salvar)}>
          <div className="form-group mt-2">
            <label htmlFor="categoria_nome">Nome:</label>
            <input
              type="text"
              className="form-control"
              id="categoria_nome"
              required
              {...register("categoria_nome")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="categoria_descricao">Descrição:</label>
            <textarea
              className="form-control"
              id="categoria_descricao"
              required
              {...register("categoria_descricao")}
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

export default CadastrarCategoria;
