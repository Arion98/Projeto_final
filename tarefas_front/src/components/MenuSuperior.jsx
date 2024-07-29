import React, { useState, useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { useAuth } from './AuthProvider';
import { api } from "../config_axios";

const MenuSuperior = () => {
    const { userId, logout } = useAuth();
    const [clienteNome, setClienteNome] = useState('');

    useEffect(() => {
        const fetchClienteNome = async () => {
            try {
                const response = await fetch(`http://localhost:8080/cliente/${userId}`, {
                    headers: {
                        'Content-Type': 'application/json'
                    }
                });

                if (response.ok) {
                    const data = await response.json();
                    setClienteNome(data.cliente_nome);
                } else {
                    console.error('Erro ao buscar nome do cliente:', response.status);
                }
            } catch (error) {
                console.error('Erro ao buscar nome do cliente:', error);
            }
        };

        if (userId) {
            fetchClienteNome();
        }
    }, [userId]);
    
    return (
        <nav className="navbar navbar-expand-sm bg-primary navbar-dark sticky-top">
            <div className="container">
                <Link to={`/welcome/${userId}`} className="navbar-brand">Página inicial </Link>
                
                <ul className="navbar-nav">
                    
                    <li className="nav-item">
                        <Link to="/agendamento" className="nav-link">Cadastrar agendamento</Link>
                    </li>                  
                    <li className="nav-item">
                        <Link to="/lista" className="nav-link">Lista de agendamento</Link>
                    </li>
                    <li className="nav-item">
                        <button onClick={logout} className="btn  btn-outline-secondary">Logout</button>
                    </li>
                </ul>
            </div>
        </nav>
    );
};

export default MenuSuperior;
