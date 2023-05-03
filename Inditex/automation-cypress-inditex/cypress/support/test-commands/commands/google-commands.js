import Actions from '../../core/ui-actions/Actions';
import { GoogleSelectors } from '../selectors/google-selectors';


let actions = new Actions();

Cypress.Commands.add('searchInGoogle', (stringSearch) => {
    actions.shouldVisible(GoogleSelectors.inputSearch);
    actions.typeOn(GoogleSelectors.inputSearch, `${stringSearch} {enter}`);
});
Cypress.Commands.add('viewWikipediaHistoryResult', () => {
    cy.get(GoogleSelectors.wikipediaHistoryResult, { timeout: 15000 }).scrollIntoView();
    actions.shouldVisible(GoogleSelectors.wikipediaHistoryResult);
});
Cypress.Commands.add('checkYear', () => {
    actions.clickOn(GoogleSelectors.wikipediaHistoryResult);
    cy.get(GoogleSelectors.wikipediaHistoryResult).find(GoogleSelectors.wikipediaHistoryText).then(($result) => {
        var resultText = $result.text();
        cy.log(resultText);
    });
    cy.get(GoogleSelectors.wikipediaHistoryResult).screenshot();
});
Cypress.Commands.add('goToWikipediaResult', () => {
    cy.get(GoogleSelectors.wikipediaResult).scrollIntoView();
    actions.clickOn(GoogleSelectors.wikipediaResult);
});