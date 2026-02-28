module.exports = {
    navigateToTipForm: async (page) => {
        await page.locator("//a[contains(text(), ' ანონიმური მხილება ')]").first().click();
        await page.waitForLoadState('networkidle');
        await page.locator("a[href*='anonymous-hotline']").filter({ hasText: 'დაცული ვებ გვერდი' }).click();
        await page.waitForLoadState('networkidle');
        await page.waitForSelector('span.form-checkbox-item', { timeout: 15000 });
    },
    clickStayAnonymous: async (page) => {
        await page.getByText('მსურს დავრჩე ანონიმად').first().click();
        await page.waitForTimeout(500);
    },
    inputEmail: async (page, email) => {
        await page.locator('span.form-sub-label-container > input.form-textbox').fill(email);
        await page.waitForTimeout(500);
    },
    selectLocation: async (page) => {
        await page.getByText('თიბისი ბანკი').first().click();
        await page.waitForTimeout(500);
    },
    clickIncidents: async (page, count) => {
        const incidents = page.locator('span.form-checkbox-item');
        const total = await incidents.count();
        for (let i = 0; i < count && i < total; i++) {
            await incidents.nth(i).click();
            await page.waitForTimeout(200);
        }
    },
    inputDetails: async (page, details) => {
        await page.locator('.form-textarea').fill(details);
        await page.waitForTimeout(500);
    }
};