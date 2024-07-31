import React, { useEffect, useState } from 'react';
import axios from 'axios';
import '../css/Welcome.css';
import { Link, useParams } from 'react-router-dom';

const Welcome = () => {
    const [cliente, setCliente] = useState(null);
    const [error, setError] = useState(null);
    const { userId } = useParams();

    useEffect(() => {
        const fetchCliente = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/cliente/${userId}`);
                setTimeout(() => {
                    setCliente(response.data);
                }, 3000); // 3000 ms = 3 segundo
            } catch (error) {
                console.error('Error fetching client data:', error);
                setError('Erro ao carregar dados do cliente');
            }
        };

        fetchCliente();
    }, [userId]);

    if (error) {
        return <div>{error}</div>;
    }

    if (!cliente) {
        return (
            <div className="loading-container">
                <div className="loading-image" />
                <p>Carregando<span className="loading-dots"></span></p>
            </div>
        );
    }

    return (
        <div className="welcome-container">
            <div className="welcome-message">
                <h2>Nome do Cliente: {cliente.cliente_nome}</h2>
                <p>Estamos felizes em vê-lo aqui.</p>
                <Link to="/agendamento" className="welcome-link">Cadastrar Agendamento</Link>
                <Link to="/lista" className="welcome-link">Lista de Agendamento</Link>
            </div>
        </div>
    );
};

export default Welcome;
