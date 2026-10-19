import { useState } from "react";
import api from "../api/api";

function StrategyCard() 
    const [signals, setSignals] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const runStrategy = async () => 
        try {
            setLoading(true);
            setError("");
            const response = await api.post("/strategy/run");
            setSignals(response.data);
        } catch (error) {
            console.error(error);
            setError("Failed to run strategy");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="card strategy">
            <h2>Strategy</h2>
            <button onClick={runStrategy} disabled={loading}>
                {loading ? "Running..." : "Run Strategy"}
            </button>
            {error && <p className="status-text error">{error}</p>}
            <div className="signal-list">
                {signals.map((item) => (
                    <div key={item.symbol} className="signal-row">
                        <span className="signal-symbol">{item.symbol}</span>
                        <span className={`signal-badge signal-${item.signal.toLowerCase()}`}>
                            {item.signal}
                        </span>
                    </div>
                ))}
            </div>
        </div>
    
}

export default StrategyCard;