import { PetclinicPage } from './app.po';

describe('petclinic App', () => {
  let page: PetclinicPage;

  beforeEach(() => {
    page = new PetclinicPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
