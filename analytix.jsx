import { useEffect, useState } from "react";
import api from "../api/api";

function AnalyticsCard() 
    const [analytics, setAnalytics] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");

    useEffect(() => { fetchAnalytics(); }, []);

    const fetchAnalytics = async () => 
        try {
            setLoading(true);
            setError("");
            const response = await api.get("/analytics");
            setAnalytics(response.data);
        } catch (error) {
            console.error(error);
            setError("Failed to load analytics");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="card analytics">
            <h2>Analytics</h2>
            {loading && <p className="status-text">Loading...</p>}
            {error && <p className="status-text error">{error}</p>}
            <div className="analytics-table">
                <div className="analytics-header">
                    <span>Symbol</span>
                    <span>Price Change</span>
                    <span>Volume Change</span>
                    <span>Rank</span>
                </div>
                {analytics.map((item) => (
                    <div key={item.symbol} className="analytics-row">
                        <span className="analytics-symbol">{item.symbol}</span>
                        <span>{item.price_change_pct}%</span>
                        <span>{item.volume_change_pct}%</span>
                        <span>#{item.rank}</span>
                    </div>
                ))}
            </div>
        </div>
    
}

export default AnalyticsCard;