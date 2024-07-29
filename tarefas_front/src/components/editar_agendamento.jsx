import React, { useState, useEffect } from 'react';
import { api } from '../config_axios'; // Substitua pelo caminho correto para o arquivo de configuração da API
import { useNavigate, useParams } from 'react-router-dom';

const EditarAgendamento = () => {
  const [agendamento, setAgendamento] = useState(null);
  const [clientes, setClientes] = useState([]);
  const [servicos, setServicos] = useState([]);
  const [successMessage, setSuccessMessage] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();
  const { id } = useParams();

  useEffect(() => {
    const fetchAgendamento = async () => {
      try {
        const response = await api.get(`/agendamento/${id}`);
        setAgendamento(response.data);
      } catch (error) {
        console.error('Erro ao buscar agendamento:', error);
      }
    };

    const fetchClientes = async () => {
      try {
        const response = await api.get('/cliente'); // Substitua pelo endpoint correto
        setClientes(response.data);
      } catch (error) {
        console.error('Erro ao buscar clientes:', error);
      }
    };

    const fetchServicos = async () => {
      try {
        const response = await api.get('/servico'); // Substitua pelo endpoint correto
        setServicos(response.data);
      } catch (error) {
        console.error('Erro ao buscar serviços:', error);
      }
    };

    fetchAgendamento();
    fetchClientes();
    fetchServicos();
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setAgendamento((prevAgendamento) => ({
      ...prevAgendamento,
      [name]: value
    }));
  };

  const handleSave = async () => {
    try {
      // Construindo o objeto de atualização com conversão de tipos
      const updatedAgendamento = {
        cliente: {
          cliente_id: parseInt(agendamento.cliente_id, 10) // Convertendo para número
        },
        servico: {
          servico_id: parseInt(agendamento.servico_id, 10) // Convertendo para número
        },
        agendamento_hora: agendamento.agendamento_hora,
        dataAgendamento: agendamento.dataAgendamento,
        agendamento_observacoes: agendamento.agendamento_observacoes,
        agendamento_status: agendamento.agendamento_status
      };
  
      // Enviando os dados atualizados para o backend
      await api.put(`/agendamento/${id}`, updatedAgendamento);
  
      setSuccessMessage('Agendamento atualizado com sucesso.');
      setTimeout(() => {
        setSuccessMessage('');
        navigate('/lista'); // Redireciona de volta para a lista de agendamentos
      }, 1000);
    } catch (error) {
      console.error('Erro ao atualizar agendamento:', error);
      setErrorMessage('Erro ao atualizar agendamento.');
    }
  };
  return (
    <div className="container mt-4">
      <h3>Editar Agendamento</h3>
      {successMessage && <div className="alert alert-success">{successMessage}</div>}
      {errorMessage && <div className="alert alert-danger">{errorMessage}</div>}
      {agendamento && (
        <div>
          <div className="mb-3">
            <label htmlFor="cliente_id" className="form-label">Cliente</label>
            <select
              id="cliente_id"
              name="cliente_id"
              className="form-select"
              value={agendamento.cliente_id || ''}
              onChange={handleChange}
            >
              <option value="">Selecione um cliente</option>
              {clientes.map(cliente => (
                <option key={cliente.cliente_id} value={cliente.cliente_id}>
                  {cliente.cliente_nome}
                </option>
              ))}
            </select>
          </div>
          <div className="mb-3">
            <label htmlFor="servico_id" className="form-label">Serviço</label>
            <select
              id="servico_id"
              name="servico_id"
              className="form-select"
              value={agendamento.servico_id || ''}
              onChange={handleChange}
            >
              <option value="">Selecione um serviço</option>
              {servicos.map(servico => (
                <option key={servico.servico_id} value={servico.servico_id}>
                  {servico.servicoNome}
                </option>
              ))}
            </select>
          </div>
          <div className="mb-3">
            <label htmlFor="agendamento_hora" className="form-label">Hora</label>
            <input
              type="time"
              id="agendamento_hora"
              name="agendamento_hora"
              className="form-control"
              value={agendamento.agendamento_hora || ''}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="agendamento_observacoes" className="form-label">Observações</label>
            <textarea
              id="agendamento_observacoes"
              name="agendamento_observacoes"
              className="form-control"
              value={agendamento.agendamento_observacoes || ''}
              onChange={handleChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="dataAgendamento" className="form-label">Data</label>
            <input
              type="date"
              id="dataAgendamento"
              name="dataAgendamento"
              className="form-control"
              value={agendamento.dataAgendamento || ''}
              onChange={handleChange}
            />
          </div>
          <button onClick={handleSave} className="btn btn-primary">Salvar</button>
        </div>
      )}
    </div>
  );
};

export default EditarAgendamento;
