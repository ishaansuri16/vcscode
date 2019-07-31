import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPpUserMaster } from 'app/shared/model/pp-user-master.model';

@Component({
    selector: 'jhi-pp-user-master-detail',
    templateUrl: './pp-user-master-detail.component.html'
})
export class PpUserMasterDetailComponent implements OnInit {
    ppUserMaster: IPpUserMaster;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ppUserMaster }) => {
            this.ppUserMaster = ppUserMaster;
        });
    }

    previousState() {
        window.history.back();
    }
}
