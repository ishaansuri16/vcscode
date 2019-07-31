import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWalletHistory } from 'app/shared/model/wallet-history.model';
import { WalletHistoryService } from './wallet-history.service';

@Component({
    selector: 'jhi-wallet-history-delete-dialog',
    templateUrl: './wallet-history-delete-dialog.component.html'
})
export class WalletHistoryDeleteDialogComponent {
    walletHistory: IWalletHistory;

    constructor(
        private walletHistoryService: WalletHistoryService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.walletHistoryService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'walletHistoryListModification',
                content: 'Deleted an walletHistory'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-wallet-history-delete-popup',
    template: ''
})
export class WalletHistoryDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ walletHistory }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(WalletHistoryDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.walletHistory = walletHistory;
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
