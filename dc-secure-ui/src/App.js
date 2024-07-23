import "./App.css";
import { Link, NavLink, Route, Routes } from "react-router-dom";
import NavigationPage from "./components/navigation/NavigationPage";
import "./navstyles.css";
import SpringBatch from "./components/spring/batch/SpringBatch";
function App() {
  return (
    <>
      <NavigationPage />
      <SpringBatch />
    </>
  );
}

export default App;