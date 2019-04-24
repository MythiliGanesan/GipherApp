import { AppPage } from './app.po';
import { browser,by, logging } from 'protractor';

describe('gipher App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
    browser.driver.manage().window().maximize();
  });

  
  it('should display title', () => {
    page.navigateTo();
    expect(browser.getTitle()).toEqual('Gipher App');
  });

  it('should be redirected to /signin route on opening application ',() =>{
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/signin')
  });

   it('should be redirected to /signup route ',() => {
     browser.sleep(2000);
     browser.element(by.id('signup-button')).click()
     expect(browser.getCurrentUrl()).toContain('/signup')
   });

   it('it should be able to signup for the user ',() => {
     browser.element(by.id('firstName')).sendKeys('user firstName');
     browser.element(by.id('lastName')).sendKeys('user lastName');
     browser.element(by.id('userId')).sendKeys('Mythili');
     browser.element(by.id('password')).sendKeys('Mythili1234');
     browser.sleep(2000);
     browser.element(by.id('signup-user')).click()
     expect(browser.getCurrentUrl()).toContain('/signin')
   });

  it('should able to to signin and navigate to popular gifs', () => {
    browser.element(by.id('userId')).sendKeys('Mythili');
    browser.element(by.id('password')).sendKeys('Mythili1234');
    browser.element(by.id('signin-user')).click()
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/home')
  });

  it('should go to gif_page by clicking gif',() => {
    browser.element(by.id('gif-id')).click()
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/gif');
  });

  it('should add gif to favourites',() => {
    browser.executeScript('window.scrollTo(0,3000);').then( () => {
      browser.element(by.id('addBtn')).click()
    })
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/gif');
  });
  
  it('should remove from favourites',() => {
    browser.sleep(2000);
    page.navigateToFavourites();
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/favourites');
  }); 

  it('should signout and navigate to signin page',() => {
    browser.element(by.id('sign-out')).click()
    browser.sleep(2000);
    expect(browser.getCurrentUrl()).toContain('/signin');
  }); 

});
