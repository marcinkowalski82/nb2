export interface ProjectListResponse {
   content: Project[];
   totalElements: number;
}

export interface Project {
   id: number;
   name: string,
   type:string
}
