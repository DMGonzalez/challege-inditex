const { defineConfig } = require("cypress");

module.exports = defineConfig({
  chromeWebSecurity: false,
  defaultCommandTimeout: 10000,
  viewportWidth: 1280,
  viewportHeight: 720,
  screenshotOnRunFailure: true,
  projectId: "automation-cypress-capitole",
  reporter: 'mochawesome',
  reporterOptions: {
    reportDir: 'cypress/results',
    charts: true,
    reportPageTitle: 'Report',
    embeddedScreenshots: true,
    overwrite: false,
    html: true,
    json: true
  },
  e2e: {
    specPattern: "cypress/e2e/**/*.cy.js",
    setupNodeEvents(on, config) {
      const environment = config.env.environment
      const dataConfig = require(`./cypress/config/${environment}-google-search.js`);
      config.baseUrl = dataConfig.baseUrl
      config.env = dataConfig.env
      return config
    },
  },
})