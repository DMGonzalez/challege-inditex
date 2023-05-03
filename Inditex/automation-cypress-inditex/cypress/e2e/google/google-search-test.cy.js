describe(`Google search test - ${Cypress.env().environment}`, () => {
    beforeEach('Configuration', () => {
        Cypress.on('uncaught:exception', (err, runnable) => {
            return false;
        });
        cy.goToBaseUrl();
    });
    it(`Search '${Cypress.env('search')}' Google`, () => {
        cy.searchInGoogle(`${Cypress.env('search')}`).then(() => {
            cy.viewWikipediaHistoryResult();
            cy.checkYear();
        });
    });
    it(`Select wikipedia result`, () => {
        cy.searchInGoogle(`${Cypress.env('search')}`).then(() => {
            cy.goToWikipediaResult();
            cy.goToHistorySection();
        });
    });
});