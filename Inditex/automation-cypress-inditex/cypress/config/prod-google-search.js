const { defineConfig } = require("cypress");

module.exports = defineConfig({
  chromeWebSecurity: false,
  defaultCommandTimeout: 10000,
  viewportWidth: 1280,
  viewportHeight: 720,
  screenshotOnRunFailure: true,
  projectId: "google-search-test",
  e2e: {
    specPattern: "cypress/e2e/**/*.cy.js",
    setupNodeEvents(on, config) {
      return config
    },
  },
  env: {
    baseUrl: `https://www.google.com/`,
    search: "Automatización"
  },
});