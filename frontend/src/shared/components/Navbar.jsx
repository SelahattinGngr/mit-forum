import { useTranslation } from "react-i18next";
import logo from "@/assets/bayrak.png";
import { Link } from "react-router-dom";

export function Navbar() {
  const { t } = useTranslation();

  return (
    <nav className="navbar navbar-expand bg-body-tertiary shadow-sm">
      <div className="container-fluid">
        <Link className="navbar-brand" to={"/"}>
          <img src={logo} alt="logo" width={60} />
          Navbar
        </Link>
        <ul className="navbar-nav">
          <li className="nav-item">
            <Link className="nav-link" to={"/signup"}>
              {t("signup")}
            </Link>
          </li>
        </ul>
      </div>
    </nav>
  );
}
