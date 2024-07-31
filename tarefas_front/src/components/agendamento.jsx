import { useForm } from "react-hook-form";
import { api } from "../config_axios";
import { useState, useEffect } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { Helmet } from "react-helmet";

const Agendamento = () => {
    const { register, handleSubmit, reset } = useForm();
    const [aviso, setAviso] = useState("");
    const [avisoTipo, setAvisoTipo] = useState("");
    const [selectedDate, setSelectedDate] = useState(null);
    const [selectedTime, setSelectedTime] = useState("00:00");
    const [servico, setServicos] = useState([]);
    const [cliente, setClientes] = useState([]);

    useEffect(() => {
        const fetchServicos = async () => {
            try {
                const response = await api.get("/servico");
                setServicos(response.data);
            } catch (error) {
                console.error("Erro ao buscar serviços", error);
            }
        };

        const fetchClientes = async () => {
            try {
                const response = await api.get("/cliente");
                setClientes(response.data);
            } catch (error) {
                console.error("Erro ao buscar clientes", error);
            }
        };

        fetchServicos();
        fetchClientes();
    }, []);

    const salvar = async (campos) => {
        try {
            const camposCompletos = {
                agendamento_hora: selectedTime,
                agendamento_observacoes: campos.agendamento_observacoes,
                agendamento_status: campos.agendamento_status,
                dataAgendamento: selectedDate,
                servico: { servico_id: parseInt(campos.servico_id, 10) },
                cliente: { cliente_id: parseInt(campos.cliente_id, 10) }
            };
            console.log("Campos completos:", camposCompletos);
            await api.post("agendamento", camposCompletos);
            setAviso("Agendamento realizado com sucesso!");
            setAvisoTipo("success"); // Define o tipo como sucesso
            reset();
        } catch (error) {
            setAviso("Erro ao realizar agendamento!");
            setAvisoTipo("error"); // Define o tipo como erro
        }
    };

    return (
        <>
            <Helmet>
                <title>Agendamento</title>
            </Helmet>
            <div className="container-fluid bg-dark text-light min-vh-100 d-flex align-items-center">
                <div className="container p-5 bg-light text-dark rounded">
                    <h4 className="fst-italic mb-3">Agendamento</h4>
                    <form onSubmit={handleSubmit(salvar)}>
                        <select
                            className="form-select"
                            aria-label="Default select example"
                            {...register("servico_id")}
                            defaultValue=""
                        >
                            <option value="" disabled>Selecione um serviço</option>
                            {servico.map(servico => (
                                <option key={servico.servico_id} value={servico.servico_id}>{servico.servicoNome}</option>
                            ))}
                        </select>
                        <br />
                        <select
                            className="form-select"
                            aria-label="Selecione um cliente"
                            {...register("cliente_id")}
                            defaultValue=""
                        >
                            <option value="" disabled>Selecione um cliente</option>
                            {cliente.map(cliente => (
                                <option key={cliente.cliente_id} value={cliente.cliente_id}>{cliente.cliente_nome}</option>
                            ))}
                        </select>
                        <br />
                        <div>
                            <label>Escolha a data que você deseja agendar o Serviço:</label>
                            <br />
                            <DatePicker
                                selected={selectedDate}
                                onChange={(date) => setSelectedDate(date)}
                                dateFormat="dd/MM/yyyy"
                                className="form-control"
                            />
                        </div>
                        <br />
                        <h6>Selecione um horário:</h6>
                        <div className="input-group mb-3">
                            <input
                                type="time"
                                className="form-control"
                                value={selectedTime}
                                onChange={(e) => setSelectedTime(e.target.value)}
                            />
                        </div>
                        <br />
                        <select
                            className="form-select"
                            aria-label="Selecione um status"
                            {...register("agendamento_status")}
                            defaultValue=""
                        >
                            <option value="" disabled>Selecione um status</option>
                            <option value="ESPERA">Em Espera</option>
                            <option value="ANDAMENTO">Em Andamento</option>
                            <option value="FINALIZADO">Finalizado</option>
                        </select>
                        <br />
                        <div className="mb-3">
                            <label htmlFor="agendamento_observacoes" className="form-label">Observações:</label>
                            <textarea
                                className="form-control"
                                id="agendamento_observacoes"
                                rows="3"
                                {...register("agendamento_observacoes")}
                            ></textarea>
                        </div>
                        <button type="submit" className="btn btn-primary">Agendar</button>
                    </form>
                    <div className={`alert mt-3 ${avisoTipo === "success" ? "alert-success" : avisoTipo === "error" ? "alert-danger" : ""}`}>
                        {aviso}
                    </div>
                </div>
            </div>
        </>
    );
};

export default Agendamento;
