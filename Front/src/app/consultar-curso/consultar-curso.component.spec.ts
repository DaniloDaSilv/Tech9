import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultarCursoComponent } from './consultar-curso.component';

describe('ConsultarCursoComponent', () => {
  let component: ConsultarCursoComponent;
  let fixture: ComponentFixture<ConsultarCursoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultarCursoComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultarCursoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
