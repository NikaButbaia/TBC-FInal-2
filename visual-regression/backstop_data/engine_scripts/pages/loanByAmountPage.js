module.exports = {
    navigateTo: async (page) => {
        await page.getByText('ჩემთვის').first().hover();
        await page.waitForTimeout(1500);
        await page.getByText('სამომხმარებლო').click();
        await page.waitForTimeout(1500);
        await page.locator("a[href*='consumer-loan/digital']").click();
        await page.waitForLoadState('networkidle');
    },
    inputLoanAmount: async (page, amount) => {
        await page.locator("input[type='number'][min='200']").fill(amount);
        await page.waitForTimeout(1000);
    },
    inputLoanPeriod: async (page, period) => {
        await page.locator("input[type='number'][min='3']").fill(period);
        await page.waitForTimeout(1000);
    },//lazy ელემენტების გამო ეს ორი ფუნქცია ძალიან მახინჯი და დიდი გამოვიდამ მაგრამ მარტო ესე
     // იპასებოდა ყოველთვის
    waitForCalculation: async (page) => {
        await page.waitForSelector('.tbcx-pw-calculated-info__number--new', { timeout: 15000 });
        await page.waitForFunction(() => {
            const images = Array.from(document.querySelectorAll('img'));
            const visibleImages = images.filter(img => img.offsetWidth > 0 && img.offsetHeight > 0);
            return visibleImages.every(img => img.complete && img.naturalHeight !== 0);
        }, { timeout: 15000 });
        await page.waitForTimeout(8000);
    },
    scrollToLoadImages: async (page) => {
        await page.evaluate(async () => {
            await new Promise((resolve) => {
                let totalHeight = 0;
                const distance = 300;
                const timer = setInterval(() => {
                    window.scrollBy(0, distance);
                    totalHeight += distance;
                    if (totalHeight >= document.body.scrollHeight) {
                        window.scrollTo(0, 0);
                        clearInterval(timer);
                        resolve();
                    }
                }, 100);
            });
        });
        await page.waitForTimeout(2000);
    },
};