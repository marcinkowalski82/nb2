export interface SchedulerListResponse {
   content: Scheduler[];
   totalElements: number;
}

export interface Scheduler {
   id: number;
   name: string,
   cron: string,
   enabled: boolean,
   className: string
}
