import { useState } from "react";
import logoImage from "../../images/studentpool.png";
import "../navigation/NavigationStyle.css";

const NavigationPage = () => {
  const [clicked, setClicked] = useState(false);
  const sideNavBar = () => {
    setClicked(!clicked);
  };
  return (
    <>
      <nav>
        <div>
          <ul className={clicked ? "navbar active" : "navbar"}>
            <li>
              <a href="/test" className="active">
                About
              </a>
            </li>
            <li>
              <a href="/test">Spring</a>
            </li>
            <li>
              <a href="/test">Java</a>
            </li>
            <li>
              <a href="/test">Hibernet</a>
            </li>
          </ul>
        </div>
        <div className="mobile">
          <i
            onClick={sideNavBar}
            className={clicked ? "fas fa-times" : "fas fa-bars"}
          ></i>
        </div>
      </nav>
    </>
  );
};
export default NavigationPage;
