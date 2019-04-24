export class Giphy{
    public id : string;
    public type: string;
    public slug : string;
    public url : string;
    public bitly_url : string;
    public embed_url : string;
    public username : string;
    public source : string;
    public rating : string;
    public content_url : string;
    public user : {
        avatar_url : string;
        banner_url: string;
        profile_url : string;
        username : string;
        display_name : string;
        twitter : string;
    };
    public source_tld : string;
    public source_post_url : string;
    public update_datetime : string;
    public create_datetime : string;
    public import_datetime : string;
    public trending_datetime : string;
    public title : string;
    public posterPath : string;
    public offset : number;
    public total_count : number;
    public count : number;
}