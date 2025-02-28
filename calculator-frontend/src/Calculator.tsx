import React, { useState } from "react";
import "./Calculator.css";

export default function Calculator() {
  const [num1, setNum1] = useState<string>("");
  const [num2, setNum2] = useState<string>("");
  const [result, setResult] = useState<number | null>(null);
  const [error, setError] = useState<string | null>(null);

  const handleOperation = async (operation: "add" | "subtract") => {
    if (num1.length > 20 || num2.length > 20) {
      setError("Numbers cannot exceed 20 characters in length.");
      return;
    }

    try {
        const response = await fetch("http://localhost:5000/calculate", {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify({ num1: num1 || "0", num2: num2 || "0", operation }),
        });
  
        if (response.status === 400) {
          const errorData = await response.json();
          setError(errorData.error);
          return;
        }
  
        const data = await response.json();
        setResult(data.result);
        setError(null);
      } catch (err) {
        setError("Server is currently uncontactable. Please try again later.");
      }
  };

  return (
    <div className="calculator">
      <h1>Simple Calculator</h1>
      <input
        type="number"
        placeholder="Enter first number"
        value={num1}
        onChange={(e) => setNum1(e.target.value)}
      />
      <input
        type="number"
        placeholder="Enter second number"
        value={num2}
        onChange={(e) => setNum2(e.target.value)}
      />
      <div className="button-group">
        <button className="add-button" onClick={() => handleOperation("add")}>Add</button>
        <button className="subtract-button" onClick={() => handleOperation("subtract")}>Subtract</button>
      </div>
      {result !== null && <div className="result">Result: {result}</div>}
      {error && <div className="error-popup">{error}</div>}
    </div>
  );
}