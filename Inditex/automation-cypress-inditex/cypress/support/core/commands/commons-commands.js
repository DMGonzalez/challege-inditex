import Actions from '../../core/ui-actions/Actions';

let actions = new Actions();

Cypress.Commands.add('goTo', (url) => {
    cy.window().then(win => {
        return win.open(url, '_self');
    });
});
Cypress.Commands.add('goToBaseUrl', () => {
    cy.visit(`${Cypress.env('baseUrl')}`, { timeout: 35000 });
});