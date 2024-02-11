import { useTranslation } from "react-i18next";

export function LanguageSelector() {
  const { i18n } = useTranslation();

  const onChangeLanguage = (language) => {
    i18n.changeLanguage(language);
    localStorage.setItem("language", language);
  };
  return (
    <>
      <img
        style={{ cursor: "pointer" }}
        src="https://flagcdn.com/24x18/tr.png"
        width="24"
        height="18"
        alt="Turkce"
        onClick={() => onChangeLanguage("tr")}
      ></img>
      <img
        style={{ cursor: "pointer" }}
        src="https://flagcdn.com/24x18/us.png"
        width="24"
        height="18"
        alt="English"
        onClick={() => onChangeLanguage("en")}
      ></img>
    </>
  );
}
