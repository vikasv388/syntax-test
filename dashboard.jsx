import MarketTable from "../components/MarketTable";
import AnalyticsCard from "../components/AnalyticsCard";
import StrategyCard from "../components/StrategyCard";
import PriceChart from "../components/PriceChart";

function Dashboard() {
    return (
        <div className="dashboard">
            <h1>Crypto Market Dashboard</h1>

            <div className="top-row">
                <AnalyticsCard />
                <MarketTable />
                <StrategyCard />
            </div>

            <PriceChart />
        </div>
    );
}

export default Dashboard;