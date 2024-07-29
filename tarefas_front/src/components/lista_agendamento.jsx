import React, { useState, useEffect } from 'react';
import { api } from '../config_axios'; // Substitua pelo caminho correto para o arquivo de configuração da API
import { useNavigate } from 'react-router-dom';

const ListaAgendamentos = () => {
  const [agendamentos, setAgendamentos] = useState([]);
  const [successMessage, setSuccessMessage] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchAgendamentos = async () => {
      try {
        const response = await api.get('/agendamento'); // Substitua pelo endpoint correto
        setAgendamentos(response.data || []);
      } catch (error) {
        console.error('Erro ao buscar agendamentos:', error);
      }
    };

    fetchAgendamentos();
  }, []);

  const handleEdit = (id) => {
    navigate(`/agendamento/editar/${id}`);
  };

  const handleDelete = async (id) => {
    try {
      await api.delete(`/agendamento/${id}`);
      setAgendamentos(agendamentos.filter(a => a.agendamento_id !== id));
      setSuccessMessage(`Agendamento com ID ${id} excluído com sucesso.`);
      
      // Clear success message after 3 seconds
      setTimeout(() => setSuccessMessage(''), 3000);
    } catch (error) {
      console.error('Erro ao excluir agendamento:', error);
    }
  };

  return (
    <div className="container mt-4">
      <h3>Lista de Agendamentos</h3>
      {successMessage && (
        <div className="alert alert-success" role="alert">
          {successMessage}
        </div>
      )}
      {agendamentos.length === 0 ? (
        <div className="alert alert-info" role="alert">
          Não há agendamentos cadastrados.
        </div>
      ) : (
        <table className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Cliente</th>
              <th>Hora</th>
              <th>Serviço</th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {agendamentos.map(agendamento => (
              <tr key={agendamento.agendamento_id}>
                <td>{agendamento.agendamento_id}</td>
                <td>{agendamento.cliente?.cliente_nome || 'Desconhecido'}</td>
                <td>{agendamento.agendamento_hora}</td>
                <td>{agendamento.servico?.servicoNome || 'Desconhecido'}</td>
                <td>
                  <button onClick={() => handleEdit(agendamento.agendamento_id)} className="btn btn-warning btn-sm me-2">
                    Editar
                  </button>
                  <button onClick={() => handleDelete(agendamento.agendamento_id)} className="btn btn-danger btn-sm">
                    Deletar
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </div>
  );
};

export default ListaAgendamentos;
