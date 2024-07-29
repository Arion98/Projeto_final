import { useForm } from "react-hook-form";
import { api } from "../config_axios";
import { useState, useEffect } from "react";
import InputMask from "react-input-mask";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

const Cadastrar_Usuario = () => {
  const { register, handleSubmit, reset } = useForm();
  const [aviso, setAviso] = useState("");
  const [endereco, setEndereco] = useState([]);
  const [success, setSuccess] = useState(false); // Estado para controlar a mensagem de sucesso
  const navigate = useNavigate(); // Hook para navegação

  useEffect(() => {
    const fetchEndereco = async () => {
      try {
        const response = await api.get("/endereco");
        setEndereco(response.data || []); // Ajustado para garantir que seja um array
        console.log(response.data);
      } catch (error) {
        console.error("Erro ao buscar endereço:", error);
        setEndereco([]); // Garantir que seja sempre um array
      }
    };
    fetchEndereco();
  }, []);

  const salvar = async (campos) => {
    try {
      const response = await api.post('cliente', {
        cliente_nome: campos.cliente_nome,
        cliente_cpf: campos.usuario_cpf,
        email: campos.usuario_email,
        cliente_dataNascimento: campos.usuario_data_nascimento,
        cliente_senha: campos.usuario_senha,
        endereco: {
          endereco_id: parseInt(campos.usuario_endereco_id, 10) // Convertendo para número
        }
      });
      reset();
      setSuccess(true);
      setTimeout(() => {
        navigate("/");
      }, 2000);
      setAviso('Usuário cadastrado com sucesso!');
    } catch (error) {
      // Tratar erros de requisição
      console.error('Erro ao cadastrar usuário:', error);
      setAviso('Erro ao cadastrar usuário. Por favor, tente novamente!');
    }
  };

  return (
    <div className="container-fluid bg-dark text-light min-vh-100 d-flex align-items-center">
      <div className="container p-5 bg-light text-dark rounded">
        <h4 className="fst-italic mb-3">Cadastrar Usuário</h4>
        <form onSubmit={handleSubmit(salvar)}>
          <div className="form-group mt-2">
            <label htmlFor="cliente_nome">Nome:</label>
            <input
              type="text"
              className="form-control"
              id="cliente_nome"
              required
              {...register("cliente_nome")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="usuario_cpf">CPF:</label>
            <InputMask
              mask="999.999.999-99"
              className="form-control"
              id="usuario_cpf"
              required
              {...register("usuario_cpf")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="usuario_data_nascimento">Data de nascimento:</label>
            <input
              type="date"
              className="form-control"
              id="usuario_data_nascimento"
              required
              {...register("usuario_data_nascimento")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="usuario_email">Email:</label>
            <input
              type="email"
              className="form-control"
              id="usuario_email"
              required
              {...register("usuario_email")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="usuario_senha">Senha:</label>
            <input
              type="password"
              className="form-control"
              id="usuario_senha"
              required
              {...register("usuario_senha")}
            />
          </div>
          {endereco.length > 0 && (
            <div className="form-group mt-2">
              <label htmlFor="usuario_endereco_id">Endereço:</label>
              <select
                className="form-control"
                id="usuario_endereco_id"
                required
                {...register("usuario_endereco_id")}
              >
                <option defaultValue="" selected disabled>
                  Selecione um endereço
                </option>
                {endereco.map((enderecos) => (
                  <option
                    key={enderecos.endereco_id}
                    value={enderecos.endereco_id}
                  >
                    {enderecos.endereco_cep}
                  </option>
                ))}
              </select>
            </div>
          )}
          <div className="d-flex">
            <button
              type="submit"
              className="btn btn-primary mt-3 me-2"
              style={{ width: '100px' }} // Ajuste a largura conforme necessário
            >
              Enviar
            </button>
            <Link to="/" className="nav-link">
              <button
                type="reset"
                className="btn btn-danger mt-3"
                style={{ width: '100px' }} // Ajuste a largura conforme necessário
              >
                Voltar
              </button>
            </Link>
          </div>

        </form>
        {aviso && (
          <div
            className={`alert mt-3 ${success ? "alert-success" : "alert-danger"
              }`}
          >
            {aviso}
          </div>
        )}
      </div>
    </div>
  );
};

export default Cadastrar_Usuario;
