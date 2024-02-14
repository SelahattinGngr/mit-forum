/* eslint-disable react/prop-types */
export function Alert({ children, styleType, center }) {
  return (
    <div
      className={`alert alert-${styleType ?? "success"} ${
        center ? "text-center" : ""
      }`}
    >
      {children}
    </div>
  );
}
