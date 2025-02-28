export default {
    transform: {
      "^.+\\.(ts|tsx)$": "babel-jest"
    },
    testEnvironment: "jsdom",
    moduleFileExtensions: ["ts", "tsx", "js", "jsx"],
    setupFilesAfterEnv: ["@testing-library/jest-dom"],
    moduleNameMapper: {
      "\\.(css|less)$": "identity-obj-proxy"
    }
  };