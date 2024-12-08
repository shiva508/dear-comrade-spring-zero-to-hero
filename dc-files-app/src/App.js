import logo from "./logo.svg";
import "./App.css";
import UploadFile from "./upload/UploadFile";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <UploadFile />
      </header>
    </div>
  );
}

export default App;
