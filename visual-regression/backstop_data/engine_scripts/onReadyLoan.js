const loanByAmountPage = require('./pages/loanByAmountPage');

module.exports = async (page, scenario) => {
    await page.waitForLoadState('networkidle');
    await loanByAmountPage.navigateTo(page);
    await loanByAmountPage.inputLoanAmount(page, '5000');
    await loanByAmountPage.inputLoanPeriod(page, '24');
    await loanByAmountPage.scrollToLoadImages(page);
    await loanByAmountPage.waitForCalculation(page);
};