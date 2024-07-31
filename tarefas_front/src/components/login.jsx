import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useAuth } from './AuthProvider';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../css/FormularioLogin.css'; // Ensure this CSS file contains your custom styles

const FormularioLogin = () => {
    const [email, setEmail] = useState("");
    const [cliente_senha, setClienteSenha] = useState("");
    const [error, setError] = useState("");
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (email.trim() === "" || cliente_senha.trim() === "") {
            setError("Preencha todos os campos!");
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email, cliente_senha })
            });

            if (response.status === 200) {
                const data = await response.json();
                login(data.token, data.userId);
                navigate(`/welcome/${data.userId}`);
            } else {
                setError("Usuário ou senha inválidos!");
            }
        } catch (error) {
            setError("Erro ao tentar logar. Tente novamente mais tarde.");
        }
    };

    return (
        <div className="login-container">
            <form onSubmit={handleSubmit} className="form-login">
                <ul className="login-nav">
                    <li className="login-nav__item active">
                        <a href="#">Sign In</a>
                    </li>
                    <li className="login-nav__item">
                        <a href="#">Sign Up</a>
                    </li>
                </ul>
                <label htmlFor="username" className="login__label">
                    Username
                </label>
                <input
                    type="text"
                    id="username"
                    className="login__input"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                />
                <label htmlFor="senha" className="login__label">
                    Password
                </label>
                <input
                    type="password"
                    id="senha"
                    className="login__input"
                    value={cliente_senha}
                    onChange={(e) => setClienteSenha(e.target.value)}
                />
                <label htmlFor="login-sign-up" className="login__label--checkbox">
                    <input id="login-sign-up" type="checkbox" className="login__input--checkbox" />
                    Keep me Signed in
                </label>
                <button type="submit" className="login__submit">Sign in</button>
            </form>
            <a href="#" className="login__forgot">Forgot Password?</a>
            {error && (
                <div className="alert alert-danger mt-3">{error}</div>
            )}
            <div className="links-container">
                <a href='http://localhost:5173/endereco' className="btn-link-custom">Ainda não possui login? Cadastre-se aqui</a>
                <a href='http://localhost:5173/enderecoTo' className="btn-link-custom">Cadastre-se como um Prestador de Serviços</a>
            </div>
        </div>
    );
};

export default FormularioLogin;
