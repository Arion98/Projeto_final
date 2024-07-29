import { useForm } from "react-hook-form";
import { api } from "../config_axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import '../css/CadastrarEndereco.css';

const CadastrarEndereco = () => {
  const { register, handleSubmit, reset } = useForm();
  const [aviso, setAviso] = useState("");
  const [success, setSuccess] = useState(false); // Estado para controlar a mensagem de sucesso
  const navigate = useNavigate(); // Hook para navegação

  const salvar = async (campos) => {
    try {
      const response = await api.post('endereco', {
        endereco_rua: campos.endereco_rua,
        endereco_cep: campos.endereco_cep,
        endereco_numero: campos.endereco_numero,
        endereco_complemento: campos.endereco_complemento,
        endereco_cidade: campos.endereco_cidade,
        endereco_estado: campos.endereco_estado,
        endereco_bairro: campos.endereco_bairro,
      });
      reset();
      setSuccess(true);
      setTimeout(() => {
        navigate("/cadastrarUsuario");
      }, 3000);
      setAviso('Endereço cadastrado com sucesso!');
    } catch (error) {
      // Tratar erros de requisição
      console.error('Erro ao cadastrar endereço:', error);
      setAviso('Erro ao cadastrar endereço. Por favor, tente novamente!');
    }
  };

  return (
    <div className="container-fluid bg-dark text-light min-vh-100 d-flex align-items-center">
      <div className="container p-5 bg-light text-dark rounded">
        <h4 className="fst-italic mb-3">Cadastrar Endereço</h4>
        <form onSubmit={handleSubmit(salvar)}>
          <div className="form-group mt-2">
            <label htmlFor="endereco_rua">Rua:</label>
            <input
              type="text"
              className="form-control"
              id="endereco_rua"
              required
              {...register("endereco_rua")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="endereco_cep">CEP:</label>
            <input
              type="text"
              className="form-control"
              id="endereco_cep"
              required
              {...register("endereco_cep")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="endereco_numero">Número:</label>
            <input
              type="number"
              className="form-control"
              id="endereco_numero"
              required
              {...register("endereco_numero")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="endereco_complemento">Complemento:</label>
            <input
              type="text"
              className="form-control"
              id="endereco_complemento"
              {...register("endereco_complemento")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="endereco_cidade">Cidade:</label>
            <input
              type="text"
              className="form-control"
              id="endereco_cidade"
              required
              {...register("endereco_cidade")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="endereco_estado">Estado:</label>
            <input
              type="text"
              className="form-control"
              id="endereco_estado"
              required
              {...register("endereco_estado")}
            />
          </div>
          <div className="form-group mt-2">
            <label htmlFor="endereco_bairro">Bairro:</label>
            <input
              type="text"
              className="form-control"
              id="endereco_bairro"
              required
              {...register("endereco_bairro")}
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

export default CadastrarEndereco;
