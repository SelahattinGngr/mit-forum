import ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import "./locales";
import router from "./router";
import "./styles.scss";

ReactDOM.createRoot(document.getElementById("root")).render(
  <RouterProvider router={router} />
);
