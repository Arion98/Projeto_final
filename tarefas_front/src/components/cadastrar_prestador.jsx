import React, { useState, useEffect } from "react";
import { useForm } from "react-hook-form";
import { api } from "../config_axios";
import { useNavigate, Link } from "react-router-dom";
import InputMask from "react-input-mask";
import { Helmet } from "react-helmet";

const Cadastrar_prestador = () => {
  const { register, handleSubmit, reset } = useForm();
  const [aviso, setAviso] = useState("");
  const [success, setSuccess] = useState(false);
  const [endereco, setEndereco] = useState([]);
  const navigate = useNavigate(); // Importar e usar o useNavigate

  useEffect(() => {
    const fetchEndereco = async () => {
      try {
        const response = await api.get("/endereco");
        setEndereco(response.data || []);
      } catch (error) {
        console.error("Erro ao buscar endereço:", error);
        setEndereco([]);
      }
    };
    fetchEndereco();
  }, []);

  const salvar = async (campos) => {
    try {
      await api.post("prestador", {
        prestador_nome: campos.prestador_nome,
        prestador_cpf: campos.prestador_cpf,
        prestador_cnpj: campos.prestador_cnpj,
        prestador_razaoSocial: campos.prestador_razaoSocial,
        prestadorEmail: campos.prestador_email,
        prestador_senha: campos.prestador_senha,
        endereco: {
          endereco_id: parseInt(campos.prestador_endereco_id, 10)
        }
      });

      setSuccess(true);
      setAviso("Prestador cadastrado com sucesso!");
      setTimeout(() => {
        navigate("/categoria");
      }, 2000);
    } catch (error) {
      console.error('Erro ao cadastrar prestador:', error);
      setAviso("Dados inválidos, tente novamente!");
      setSuccess(false);
    }
  };

  return (
    <>
      <Helmet>
        <title>Cadastro de Prestadores</title>
      </Helmet>
      <div className="container-fluid bg-dark text-light min-vh-100 d-flex align-items-center">
        <div className="container p-5 bg-light text-dark rounded">
          <h4 className="fst-italic mb-3">Preencha os campos para se cadastrar</h4>
          <form onSubmit={handleSubmit(salvar)}>
            <div className="row">
              <div className="col">
                <label htmlFor="prestador_nome">Nome:</label>
                <input
                  type="text"
                  className="form-control"
                  id="prestador_nome"
                  required
                  autoFocus
                  {...register("prestador_nome")}
                />
              </div>
              <div className="col">
                <label htmlFor="prestador_cnpj">CNPJ:</label>
                <InputMask
                  mask="99.999.999/9999-99"
                  type="text"
                  className="form-control"
                  id="prestador_cnpj"
                  {...register("prestador_cnpj")}
                />
              </div>
              <div className="col">
                <label htmlFor="prestador_cpf">CPF:</label>
                <InputMask
                  mask="999.999.999-99"
                  type="text"
                  className="form-control"
                  id="prestador_cpf"
                  required
                  {...register("prestador_cpf")}
                />
              </div>
            </div>
            <div className="row">
              <div className="col">
                <label htmlFor="prestador_email">Email:</label>
                <input
                  type="email"
                  className="form-control"
                  id="prestador_email"
                  required
                  {...register("prestador_email")}
                />
              </div>
              <div className="col">
                <label htmlFor="prestador_razaoSocial">Razão Social:</label>
                <input
                  type="text"
                  className="form-control"
                  id="prestador_razaoSocial"
                  {...register("prestador_razaoSocial")}
                />
              </div>
            </div>
            <div className="row">
              <div className="col">
                <label htmlFor="prestador_senha">Senha:</label>
                <input
                  type="password"
                  className="form-control"
                  id="prestador_senha"
                  required
                  {...register("prestador_senha")}
                />
              </div>
              {endereco.length > 0 && (
                <div className="form-group mt-2">
                  <label htmlFor="prestador_endereco_id">Endereço:</label>
                  <select
                    className="form-control"
                    id="prestador_endereco_id"
                    required
                    {...register("prestador_endereco_id")}
                  >
                    <option value="" disabled>
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
            </div>
            <div className="mt-3">
              <input
                type="submit"
                className="btn btn-primary me-2"
                value="Cadastrar"
              />
              <Link to="/">
                <input
                  type="reset"
                  className="btn btn-danger"
                  value="Cancelar"
                />
              </Link>
            </div>
          </form>
          {aviso && (
            <div
              className={`alert mt-3 ${
                success ? "alert-success" : "alert-danger"
              }`}
            >
              {aviso}
            </div>
          )}
        </div>
      </div>
    </>
  );
};

export default Cadastrar_prestador;
