require('cypress-xpath')

class Actions {

    /**
     * 
     * @param {dict} locator Diccionario que contiene una Key 'locator' con el valor de un String 
     * y un segundo Key 'by' que contiene el modo en que se detectara dicho elemento (constains o xpath)
     * 
     * @returns Un objeto Chaineable del DOM para que pueda ser utilizado para realizar acciones sobre el mismo.
     * 
     * @example this.getElement("#id_locator")
     * @example this.getElement({locator:"//xpath_locator",by:"xpath"})
     * @example this.getElement({"locator:"contains_text",by:"contains"})
     */
    #getElement(locator) {
        if (locator.by == 'contains') return cy.contains(locator.locator)
        if (locator.by == 'xpath') return cy.xpath(locator.locator)
        return cy.get(locator)
    }

    /**
     * 
     * @param {dict} locator Diccionario que contiene una Key 'locator' con el valor de un String 
     * y un segundo Key 'by' que contiene el modo en que se detectara dicho elemento (constains o xpath)
     * 
     * Verifica que el elemento se encuentra Habilitado y luego realiza la accion de Click sobre el locator recibido.
     * 
     * @example Actions().clickOn("#id_locator")
     * @example Actions().clickOn({locator:"//xpath_locator",by:"xpath"})
     * @example Actions().clickOn({"locator:"contains_text",by:"contains"})
     */
    clickOn(locator) {
        this.shouldVisible(locator).click()
    }

    /**
     * 
     * @param {dict} locator Diccionario que contiene una Key 'locator' con el valor de un String 
     * y un segundo Key 'by' que contiene el modo en que se detectara dicho elemento (constains o xpath)
     * 
     * @param {String} text String que contiene el texto a ser ingresado en el locator enviado.
     * 
     * Verifica que el elemento se encuentra Visible y luego realiza la accion de Escribrir sobre el locator recibido.
     * 
     * @example Actions().typeOn("#id_locator","Texto a escribir")
     * @example Actions().typeOn({locator:"//xpath_locator",by:"xpath"},"Texto a escribir")
     * @example Actions().typeOn({"locator:"contains_text",by:"contains"},"Texto a escribir")
     */
    typeOn(locator, text) {
        this.shouldVisible(locator).type(text)
    }

    /**
     * 
     * @param {dict} locator Diccionario que contiene una Key 'locator' con el valor de un String 
     * y un segundo Key 'by' que contiene el modo en que se detectara dicho elemento (constains o xpath)
     * 
     * Verifica que el elemento se encuentra Visible.
     * 
     * @example Actions().shouldVisible("#id_locator")
     * @example Actions().shouldVisible({locator:"//xpath_locator",by:"xpath"})
     * @example Actions().shouldVisible({"locator:"contains_text",by:"contains"})
     */
    shouldVisible(locator) {
        return this.#getElement(locator).should('be.visible')
    }
}

export default Actions