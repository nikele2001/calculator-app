import React from "react";
import { render, screen, fireEvent } from "@testing-library/react";
import '@testing-library/jest-dom';
import Calculator from "./Calculator";
import fetchMock from "jest-fetch-mock";

fetchMock.enableMocks();

beforeEach(() => {
  fetchMock.resetMocks();
});

test("renders Calculator component", () => {
  render(<Calculator />);
  expect(screen.getByText("Simple Calculator")).toBeInTheDocument();
});

test("displays error when number exceeds 20 characters", async () => {
  render(<Calculator />);
  const num1Input = screen.getByPlaceholderText("Enter first number");
  const num2Input = screen.getByPlaceholderText("Enter second number");
  const addButton = screen.getByText("Add");

  fireEvent.change(num1Input, { target: { value: "1".repeat(21) } });
  fireEvent.change(num2Input, { target: { value: "100" } });
  fireEvent.click(addButton);

  expect(await screen.findByText("Numbers cannot exceed 20 characters in length.")).toBeInTheDocument();
});

test("displays result on successful calculation", async () => {
  fetchMock.mockResponseOnce(JSON.stringify({ result: 300 }));

  render(<Calculator />);
  const num1Input = screen.getByPlaceholderText("Enter first number");
  const num2Input = screen.getByPlaceholderText("Enter second number");
  const addButton = screen.getByText("Add");

  fireEvent.change(num1Input, { target: { value: "100" } });
  fireEvent.change(num2Input, { target: { value: "200" } });
  fireEvent.click(addButton);

  expect(await screen.findByText("Result: 300")).toBeInTheDocument();
});

test("displays error on server error", async () => {
  fetchMock.mockResponseOnce(JSON.stringify({ error: "Invalid operation" }), { status: 400 });

  render(<Calculator />);
  const num1Input = screen.getByPlaceholderText("Enter first number");
  const num2Input = screen.getByPlaceholderText("Enter second number");
  const addButton = screen.getByText("Add");

  fireEvent.change(num1Input, { target: { value: "100" } });
  fireEvent.change(num2Input, { target: { value: "200" } });
  fireEvent.click(addButton);

  expect(await screen.findByText("Invalid operation")).toBeInTheDocument();
});