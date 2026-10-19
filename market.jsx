import { useEffect, useState } from "react";
import api from "../api/api";

function MarketTable() {
const [markets, setMarkets] = useState([]);
const [loading, setLoading] = useState(true);
const [error, setError] = useState("");

    useEffect(() => { fetchMarkets(); }, []);

    const fetchMarkets = async () => {
        try {
            setLoading(true);
            setError("");
            const response = await api.get("/markets");
            setMarkets(response.data);
        } catch (error) {
            console.error(error);
            setError("Failed to load market data");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="card markets">
            <h2>Markets</h2>
            {loading && <p className="status-text">Loading...</p>}
            {error && <p className="status-text error">{error}</p>}
            {!loading && !error && (
                <table>
                    <thead>
                        <tr>
                            <th>Symbol</th>
                            <th>Price</th>
                            <th>Volume</th>
                        </tr>
                    </thead>
                    <tbody>
                        {markets.map((item, index) => (
                            <tr key={index}>
                                <td>{item.symbol}</td>
                                <td>{item.price}</td>
                                <td>{item.volume}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );


export default MarketTable