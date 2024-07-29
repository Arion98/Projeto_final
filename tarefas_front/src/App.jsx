import React from 'react';
import { BrowserRouter as Router, Route, Routes, Navigate, useNavigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import { AuthProvider, useAuth } from './components/AuthProvider';
import MenuSuperior from './components/MenuSuperior';
import FormularioLogin from './components/login';
import CadastrarUsuarios from './components/cadastrar_usuario';
import CadastrarPrestador from './components/cadastrar_prestador';
import CadastrarEndereco from './components/cadastrar_endereco';
import CadastrarEnderecoTo from './components/cadastrar_endereco2';
import Agendamento from './components/agendamento';
import Welcome from './components/welcome';
import CadastrarCategoria from './components/cadastrar_categoria';
import CadastrarServico from './components/Cadastrar_servico';
import ListaAgendamentos from './components/lista_agendamento';
import EditarAgendamento from './components/editar_agendamento';

const ProtectedRoute = ({ children }) => {
  const { autenticado } = useAuth();
  const navigate = useNavigate();

  if (!autenticado) {
    navigate('/');
    return null;
  }

  return children;
};

const RoutesWithAuth = () => {
  const { autenticado } = useAuth();

  return (
    <Router>
      {autenticado && <MenuSuperior />}
      <Routes>
        <Route path="/" element={<FormularioLogin />} />
        <Route path="/welcome/:userId" element={<ProtectedRoute><Welcome /></ProtectedRoute>} />
        <Route path="/cadastrarUsuario" element={<CadastrarUsuarios />} />
        <Route path="/prestador" element={<CadastrarPrestador />} />
        <Route path="/categoria" element={<CadastrarCategoria />} />
        <Route path="/servico" element={<CadastrarServico />} />
        <Route path="/endereco" element={<CadastrarEndereco />} />
        <Route path="/enderecoTo" element={<CadastrarEnderecoTo />} />
        <Route path="/agendamento/editar/:id" element={<ProtectedRoute><EditarAgendamento /></ProtectedRoute>} />
        <Route path="/agendamento" element={<ProtectedRoute><Agendamento /></ProtectedRoute>} />
        <Route path="/lista" element={<ProtectedRoute><ListaAgendamentos /></ProtectedRoute>} />
      </Routes>
    </Router>
  );
};

const App = () => {
  return (
    <AuthProvider>
      <RoutesWithAuth />
    </AuthProvider>
  );
};

export default App;
