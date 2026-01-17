import { useState } from 'react'
import axios from 'axios'
import './App.css'

function App() {
  const [salario, setSalario] = useState('')
  const [resultado, setResultado] = useState(null)
  const [erro, setErro] = useState('')
  const [loading, setLoading] = useState(false)

  const formatarMoeda = (valor) => {
    return new Intl.NumberFormat('pt-BR', {
      style: 'currency',
      currency: 'BRL'
    }).format(valor);
  }

  const calcularImposto = async (e) => {
    e.preventDefault()
    setErro('')
    setResultado(null)
    setLoading(true)

    const valorNumerico = parseFloat(salario)

    if (!salario || isNaN(valorNumerico)) {
      setErro("Digite um valor. O governo tem pressa.")
      setLoading(false)
      return
    }

    try {
      await new Promise(r => setTimeout(r, 600)); 

      const response = await axios.post('http://localhost:8080/api/imposto/calcular', {
        valor: valorNumerico,
        descricao: "Vítima do Leão",
        tipoRenda: "CLT"
      })
      
      setResultado(response.data)
    } catch (error) {
      console.error(error)
      setErro("O servidor caiu (provavelmente culpa da burocracia). Tente de novo.")
    } finally {
      setLoading(false)
    }
  }

  return (
    <div className="container">
      <header className="brand-header">
        <h1>O Sócio <span>Oculto</span></h1>
        <p>Descubra a fatia que o governo leva do seu suor.</p>
      </header>

      <div className="card">
        <form onSubmit={calcularImposto}>
          <div className="input-group">
            <label>Seu Salário Bruto</label>
            <div className="input-wrapper">
              <span className="currency-symbol">R$</span>
              <input 
                type="number" 
                step="0.01"
                placeholder="0,00" 
                value={salario}
                onChange={(e) => setSalario(e.target.value)}
                disabled={loading}
              />
            </div>
            {erro && <p className="error-msg">{erro}</p>}
          </div>

          <button type="submit" className="primary-btn" disabled={loading}>
            {loading ? 'Consultando a Receita...' : 'Calcular o Prejuízo'}
          </button>
        </form>

        {resultado && (
          <div className="result-container">
            <div className="result-row">
              <span className="label-ironic">
                {resultado.impostoDevido > 0 ? 'O governo te leva:' : 'Por enquanto, você escapou:'}
              </span>
            </div>
            
            <div className={`result-value ${resultado.impostoDevido > 0 ? 'pagar' : 'isento'}`}>
              {formatarMoeda(resultado.impostoDevido)}
            </div>

            <div className="details-grid">
              <div className="detail-item">
                <small>FAIXA DE TRIBUTAÇÃO</small>
                <strong>{resultado.faixaImposto}</strong>
              </div>
              <div className="detail-item">
                <small>ALÍQUOTA EFETIVA</small>
                <strong>{resultado.aliquotaEfetiva}%</strong>
              </div>
            </div>
          </div>
        )}
      </div>
    </div>
  )
}

export default App