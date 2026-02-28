const loanByIncomePage = require('./pages/loanByIncomePage');

module.exports = async (page, scenario) => {
    await page.waitForLoadState('networkidle');
    await loanByIncomePage.navigateTo(page);
    await loanByIncomePage.clickIncomeTab(page);
    await loanByIncomePage.inputAmount(page, '5000');
    await loanByIncomePage.inputPeriod(page, '24');
    await loanByIncomePage.scrollToLoadImages(page);
    await loanByIncomePage.waitForCalculation(page);
};