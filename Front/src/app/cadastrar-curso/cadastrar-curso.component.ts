import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { environment } from 'src/environments/environment';
import { Categoria } from '../categoria';

@Component({
  selector: 'app-cadastrar-curso',
  templateUrl: './cadastrar-curso.component.html',
  styleUrls: ['./cadastrar-curso.component.css']
})
export class CadastrarCursoComponent implements OnInit {

  mensagem: string = '';

  formCadastro!: FormGroup;

  categoria!: Categoria[];

  constructor(private httpCliente: HttpClient) { }

  ngOnInit(): void {

    this.formCadastro = new FormGroup({

      descricao: new FormControl('', [Validators.required]),
      dataInicio: new FormControl('', [Validators.required]),
      dataFim: new FormControl('', [Validators.required]),
      qtdAlunos: new FormControl('', [Validators.required]),
      categoria: new FormControl('', [Validators.required])

    })

    this.carregaCat();

  }

  carregaCat() {

    this.categoria = [
      { idCategoria: 1, categoria: 'Multiplataforma' },
      { idCategoria: 2, categoria: 'Banco de Dados' },
      { idCategoria: 3, categoria: 'Metodologia' },
      { idCategoria: 4, categoria: 'Comportamento' },
      { idCategoria: 5, categoria: 'Comunicacao' }
    ]

  }

  get form(): any {
    return this.formCadastro.controls;
  }

  onSubmit(): void {

    let curso = this.formCadastro.value;
    let categoria = { 'idCategoria': this.formCadastro.get('categoria')?.value }
    console.log(curso);

    this.httpCliente.post(environment.apiUrl + '/curso',
      this.formCadastro.value, { responseType: 'text' }).subscribe(
        data => {
          this.mensagem = data;
          this.formCadastro.reset();
        },
        e => {
          alert(e.error)  
          this.mensagem = "Cadastro nao realizado";
          console.log(e);
        }
      )
  }


}
