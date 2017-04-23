export class AppConfig {
    
    public readonly contextPath = 'http://localhost:8082/petclinic';
    public tokenName: string = "jwtToken";

    setJwtToken(token:string) {
      localStorage.setItem(this.tokenName, token);
    }

    getJwtToken():string {
      return localStorage.getItem(this.tokenName);
    }

    removeJwtToken() {
      localStorage.removeItem(this.tokenName);
    }

};