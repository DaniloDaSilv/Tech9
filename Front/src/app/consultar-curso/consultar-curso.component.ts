import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-consultar-curso',
  templateUrl: './consultar-curso.component.html',
  styleUrls: ['./consultar-curso.component.css']
})
export class ConsultarCursoComponent implements OnInit {

  constructor(private httpClient: HttpClient) { }

  curso: any[] = [];

  formPesquisa = new FormGroup({

    descricao: new FormControl('', ),
    dataInicio: new FormControl('', ),
    dataFim: new FormControl('', )

  });
  ngOnInit(): void {
    this.httpClient.get(environment.apiUrl + '/curso?descricao=' + this.formPesquisa.value.descricao + "&dataInicio=" + this.formPesquisa.value.dataInicio
    + "&dataFim=" + this.formPesquisa.value.dataFim).subscribe(
      (data) => { this.curso = data as any[]; },
      (e) => {
        console.log(e);

      }
    )
  }



  OnSubmit(): void {

    this.httpClient.get(environment.apiUrl + '/curso?descricao=' + this.formPesquisa.value.descricao + "&dataInicio=" + this.formPesquisa.value.dataInicio
      + "&dataFim=" + this.formPesquisa.value.dataFim).subscribe(
        (data) => { this.curso = data as any[]; },


        (error) => {
          console.log(error.error);
          console.log(this.curso);



        },

      )
  }
  excluir(idCurso: number): void {
    if (window.confirm('Deseja realmente excluir o curso selecionado?')) {
      this.httpClient.delete(environment.apiUrl + "/curso/" + idCurso,
        { responseType: 'text' })
        .subscribe(
          (data) => {

            alert(data); //exibir mensagem em uma janela popup
            this.ngOnInit(); //recarregar a consulta de profissionais

          },
          (e) => {
            alert(e.error)
            console.log(e);
          }
        )
    }
  }

  

}
