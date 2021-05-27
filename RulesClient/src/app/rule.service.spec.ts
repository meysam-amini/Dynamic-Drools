import { TestBed } from '@angular/core/testing';

import { RuleService } from './rule.service';

describe('RuleService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RuleService = TestBed.get(RuleService);
    expect(service).toBeTruthy();
  });
});
