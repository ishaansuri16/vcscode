import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPpUserHistory } from 'app/shared/model/pp-user-history.model';
import { PpUserHistoryService } from './pp-user-history.service';

@Component({
    selector: 'jhi-pp-user-history-delete-dialog',
    templateUrl: './pp-user-history-delete-dialog.component.html'
})
export class PpUserHistoryDeleteDialogComponent {
    ppUserHistory: IPpUserHistory;

    constructor(
        private ppUserHistoryService: PpUserHistoryService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ppUserHistoryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'ppUserHistoryListModification',
                content: 'Deleted an ppUserHistory'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-pp-user-history-delete-popup',
    template: ''
})
export class PpUserHistoryDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ ppUserHistory }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(PpUserHistoryDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.ppUserHistory = ppUserHistory;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
