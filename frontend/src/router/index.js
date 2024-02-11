import { createBrowserRouter } from "react-router-dom";
import { Home } from "../pages/home";
import { SignUp } from "../pages/SignUp";
import { App } from "../App.jsx";
import { Activation } from "../pages/Activation";

export default createBrowserRouter([
  {
    path: "/",
    Component: App,
    children: [
      {
        path: "/",
        index: true,
        Component: Home,
      },
      {
        path: "/signup",
        Component: SignUp,
      },
      {
        path: "/activation/:token",
        Component: Activation,
      },
    ],
  },
]);
