export interface Themes {
    name: string;
    cssVars: any;
}

export const day: Themes = {
    name: "Dzień",
    cssVars: {
        "--item-bg-color": "#ffff99",
        "--body-bg-color": "#fff9d7",
        "--head-bg-color": "#ffe244",
        "--button-bg-color": "#ffa200",
        "--plus-bg-color": "#0cf503",
        "--minus-bg-color": "#f50303",
        "--font-color": "#161515",
        "--bor-color": "solid #f19b06"
    }
};

export const night: Themes = {
    name: "Noc",
    cssVars: {
        "--item-bg-color": "#1b1b1a",
        "--body-bg-color": "#111010",
        "--head-bg-color": "#535353",
        "--button-bg-color": "#343131",
        "--plus-bg-color": "#54be29",
        "--minus-bg-color": "#ba0d0d",
        "--font-color": "#ffffff",
        "--bor-color": "solid #585858"
    }
};


