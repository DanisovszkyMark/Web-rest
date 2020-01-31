import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';

@Component({
  selector: 'app-are-you-sure',
  templateUrl: './are-you-sure.component.html',
  styleUrls: ['./are-you-sure.component.scss']
})
export class AreYouSureComponent implements OnInit {

  result: boolean;

  constructor(
    public dialogRef: MatDialogRef<any>
  ) { }

  ngOnInit() {
  }

  clickOk(){
    this.dialogRef.close(true);
  }

  clickNo(){
    this.dialogRef.close(false);
  }
}
