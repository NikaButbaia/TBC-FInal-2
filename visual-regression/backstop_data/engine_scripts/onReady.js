const anonymousTipPage = require('./pages/anonymousTipPage');

module.exports = async (page, scenario) => {
    await page.waitForLoadState('networkidle');
    await anonymousTipPage.navigateToTipForm(page);
    await anonymousTipPage.clickStayAnonymous(page);
    await anonymousTipPage.inputEmail(page, 'bzz@gmail.com');
    await anonymousTipPage.selectLocation(page);
    await anonymousTipPage.clickIncidents(page, 5);
    await anonymousTipPage.inputDetails(page, 'n at the Tbilisi reich.');
};