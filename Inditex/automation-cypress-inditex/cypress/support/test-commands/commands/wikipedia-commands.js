import Actions from '../../core/ui-actions/Actions';
import { WikipediaSelectors } from '../selectors/wikipedia-selectors';

let actions = new Actions();

Cypress.Commands.add('goToHistorySection', () => {
    actions.shouldVisible(WikipediaSelectors.logoDashboard);
    cy.get(WikipediaSelectors.historySection).scrollIntoView();
    actions.clickOn(WikipediaSelectors.historySection);
    cy.xpath(WikipediaSelectors.spanHistorySection).should('be.visible');
    cy.xpath(WikipediaSelectors.spanHistorySection).then(($element) => {
        var elementText = $element.text();
        cy.log(elementText);
    });
    cy.xpath(WikipediaSelectors.spanHistorySection).screenshot();
});