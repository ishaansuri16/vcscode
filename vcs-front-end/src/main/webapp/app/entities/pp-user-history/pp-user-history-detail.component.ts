import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPpUserHistory } from 'app/shared/model/pp-user-history.model';

@Component({
    selector: 'jhi-pp-user-history-detail',
    templateUrl: './pp-user-history-detail.component.html'
})
export class PpUserHistoryDetailComponent implements OnInit {
    ppUserHistory: IPpUserHistory;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ppUserHistory }) => {
            this.ppUserHistory = ppUserHistory;
        });
    }

    previousState() {
        window.history.back();
    }
}
