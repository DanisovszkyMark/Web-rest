import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef } from '@angular/material';


@Component({
  selector: 'app-reg-successfull',
  templateUrl: './reg-successfull.component.html',
  styleUrls: ['./reg-successfull.component.scss']
})

export class RegSuccessfullComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<RegSuccessfullComponent>) {  }

  ngOnInit() {
  }
}
